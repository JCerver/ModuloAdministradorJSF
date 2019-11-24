$('body').tooltip({
    selector: '[data-toggle="tooltip"]'
});

const TYPES = ['info', 'warning', 'success', 'error'];

function show_image_toast(title, content, type, delay, img) {
    $.toast({
        title: title,
        // subtitle: '11 mins ago',
        content: content,
        type: type,
        delay: delay,
        img: {
            src: img,
            height: '25px',
            class: 'rounded',
            title: 'Operación correcta',
            alt: 'Alternative'
        }
    });
}