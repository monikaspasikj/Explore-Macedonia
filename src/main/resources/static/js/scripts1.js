const backendUrl = 'http://localhost:8080/culinary-experiences/rdf';

document.addEventListener('DOMContentLoaded', () => {
    fetchRdfData();

    const searchInput = document.getElementById('search-input');
    searchInput.addEventListener('input', () => {
        const searchTerm = searchInput.value.toLowerCase().trim();
        fetchRdfData(searchTerm);
    });
});

async function fetchRdfData(searchTerm = '') {
    try {
        const response = await fetch(backendUrl);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const rdfData = await response.text();
        displayRdfData(rdfData, searchTerm);
    } catch (error) {
        console.error('Error fetching RDF data:', error);
    }
}

function displayRdfData(rdfData, searchTerm = '') {
    const rdfDisplayElement = document.getElementById('rdf-display');
    const store = $rdf.graph();
    const mimeType = 'text/turtle';

    $rdf.parse(rdfData, store, 'http://example.org/culinary_experiences/', mimeType);

    const schema = $rdf.Namespace('http://schema.org/');

    const experiences = store.statementsMatching(undefined, schema('name'), undefined);

    const experienceHtml = experiences
        .filter(dest => dest.object.value.toLowerCase().includes(searchTerm))
        .map(dest => {
            const name = dest.object.value;
            const descriptionStmt = store.any(dest.subject, schema('description'));
            const locationStmt = store.any(dest.subject, schema('location'));

            const description = descriptionStmt ? descriptionStmt.value : 'No description available';
            const location = locationStmt ? locationStmt.value.replace('http://example.org/tourist_destinations/', '') : 'Unknown location';

            return `
                <div class="experience">
                    <h3>${name}</h3>
                    <p><strong>Description:</strong> ${description}</p>
                    <p><strong>Location:</strong> ${location}</p>
                </div>
            `;
        })
        .join('');

    rdfDisplayElement.innerHTML = experienceHtml || '<p>No results found</p>';
}
