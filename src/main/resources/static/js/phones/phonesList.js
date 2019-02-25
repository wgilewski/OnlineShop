let phonesIDS = new Set();

function onClick(event) {
    const element = event.target;

    const styleAfterClick = 'btn btn-info compareButton';
    const styleBeforeClick = 'btn btn-outline-info compareButton';

    const elementId = element.id;
    const elementIdArr = elementId.split('_');
    const id = elementIdArr[elementIdArr.length - 1];

    console.log(element.id);
    if (element.classList.contains('btn-outline-info')) {
        element.setAttribute('class', styleAfterClick);
        phonesIDS.add(id);
    } else {
        element.setAttribute('class', styleBeforeClick);
        phonesIDS.delete(id);
    }
    const ids = Array.from(phonesIDS).join('_');
    console.log(ids);
}

let compareButtons = document.getElementsByClassName('compareButton');

for (const compareButton of compareButtons) {
    compareButton.addEventListener('click', onClick, false);
}

