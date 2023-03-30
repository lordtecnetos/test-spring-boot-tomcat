function autocompletePut(el, id) {
    const group = document.getElementById(id);
    group.querySelector('.autocomplete-value').value = el.dataset.value;
    group.querySelector('.autocomplete-label').value = el.dataset.label;
    group.querySelector('.autocomplete-body').innerHTML = '';
}

function autocomplete(el) {
    const split = el.dataset.urlAutocomplete.split('?');
    const params = new URLSearchParams(split[1]);
    params.append('search', el.value);

    fetch(`${split[0]}?${params}`)
        .then(r => r.json())
        .then(d => {
            const html = `
            <div style="width: 200px; border: 1px solid; margin: 5px 0;">
            ${d.map(e => `
                <div data-value="${e.value}" data-label="${e.label}">
                    <span onclick="window.autocompletePut(this.parentElement, '${el.parentElement.id}')" style="cursor: pointer;">
                        ${e.label}
                    </span>
                </div>`).join('')}
            ${!d.length ? '<span>Nenhum resultado encontrado</span>' : ''}
            <div>`;
            el.parentElement.querySelector('.autocomplete-body').innerHTML = html.trim();
        });
};
