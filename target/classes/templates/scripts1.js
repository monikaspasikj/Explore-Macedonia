const backendUrl = 'http://localhost:8080/culinary-experience/rdf';

document.addEventListener('DOMContentLoaded', () => {
    fetchRdfData();
});

async function fetchRdfData() {
    try {
        const response = await fetch(backendUrl);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const rdfData = await response.text();
        displayRdfData(rdfData);
    } catch (error) {
        console.error('Error fetching RDF data:', error);
    }
}
function displayRdfData(rdfData) {
    const rdfDisplayElement = document.getElementById('rdf-display');
    const store = $rdf.graph();
    const mimeType = 'text/turtle';

    $rdf.parse(rdfData, store, 'http://example.org/culinary_experience/', mimeType);

    const schema = $rdf.Namespace('http://schema.org/');

    const destinations = store.statementsMatching(undefined, schema('name'), undefined);

    const destinationsHtml = destinations.map(dest => {
        const name = dest.object.value;
        const descriptionStmt = store.any(dest.subject, schema('description'));
        const containedInStmt = store.any(dest.subject, schema('containedInPlace'));

        const description = descriptionStmt ? descriptionStmt.value : 'No description available';
        const containedInPlace = containedInStmt ? containedInStmt.value.replace('http://example.org/culinary_experience/', '') : 'Unknown location';

        return `
            <div class="experience">
                <h3>${name}</h3>
                <p><strong>Description:</strong> ${description}</p>
                <p><strong>Location:</strong> ${containedInPlace}</p>
            </div>
        `;
    }).join('');

    rdfDisplayElement.innerHTML = destinationsHtml;
}
