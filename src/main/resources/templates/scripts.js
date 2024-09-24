const backendUrl = 'http://localhost:8080/tourist-destinations/rdf';

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

    $rdf.parse(rdfData, store, 'http://example.org/tourist_destinations/', mimeType);

    const schema = $rdf.Namespace('http://schema.org/');

    const destinations = store.statementsMatching(undefined, schema('name'), undefined);

    const destinationsHtml = destinations
        .filter(dest => dest.object.value.toLowerCase().includes(searchTerm))
        .map(dest => {
            const name = dest.object.value;
            const descriptionStmt = store.any(dest.subject, schema('description'));
            const containedInStmt = store.any(dest.subject, schema('containedInPlace'));

            const description = descriptionStmt ? descriptionStmt.value : 'No description available';
            const containedInPlace = containedInStmt ? containedInStmt.value.replace('http://example.org/tourist_destinations/', '') : 'Unknown location';

            return `
                <div class="destination">
                    <h3>${name}</h3>
                    <p><strong>Description:</strong> ${description}</p>
                    <p><strong>Location:</strong> ${containedInPlace}</p>
                </div>
            `;
        })
        .join('');

    rdfDisplayElement.innerHTML = destinationsHtml || '<p>No results found</p>';
}
