function autocompletePut(el, id) {
    const group = document.getElementById(id);
    group.querySelector('.autocomplete-value').value = el.dataset.value;
    group.querySelector('.autocomplete-label').value = el.dataset.label;
    group.querySelector('.autocomplete-body').innerHTML = '';
}

function autocomplete(el) {
    fetch(el.dataset.urlAutocomplete)
    .then(r => r.json())
    .then(d => {
        const html = `
            ${d.map(e => `<span data-value="${e.value}" data-label="${e.label}" 
                onclick="window.autocompletePut(this, '${el.parentElement.id}')"
                style="cursor: pointer"> > ${e.label}</span>`)
                .join('')}
        `;
        el.parentNode.querySelector('.autocomplete-body').innerHTML = html;
    });
};
