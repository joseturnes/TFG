export default {
    profile: {
        name : 'Carlos Rivas',
        img: 'logo.png',
        onClick(){
            console.log('Hola');
        }
    },
    items:[
        {
            id : 0,
            title: 'Inicio',
            icon: 'fa-house-user',
            onClick() {
                console.log('Inicio');
            }
        },
        {
            id : 1,
            title: 'Información de Figuras',
            icon: 'fa-circle-info',
            onClick() {
                console.log('Info Figuras');
            }
        },
        {
            id : 2,
            title: 'Iniciativas/Agrupacións',
            icon: 'fa-people-group',
            onClick() {
                console.log('Iniciativas/Agrupacións');
            }
        },
        {
            id : 3,
            title: 'Curiosidades',
            icon: 'fa-circle-question',
            onClick() {
                console.log('Curiosidades');
            }
        },
        {
            id : 4,
            title: 'Arte e Astronomía',
            icon: 'fa-moon',
            onClick() {
                console.log('Arte e Astronomía');
            }
        },
        {
            id : 5,
            title: 'Galería de Imaxes',
            icon: 'fa-images',
            onClick() {
                console.log('Galería de Imaxes');
            }
        }
    ]
}