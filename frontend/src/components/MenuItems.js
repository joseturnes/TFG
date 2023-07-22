export default {
    profile: {
        name : 'Administracion',
        img: 'stellarium.png',
        onClick(router){
            console.log('Hola ' + this.name);
            console.log(router);
            router.push('/administracion');
        }
    },
    setProfileName(userName) {
        if (userName) {
            this.profile.name = userName;
            console.log(this.profile.name);
        } else {
            this.profile.name = 'Iniciar sesión';
        }
    },
    created() {
        // Obtener la información del usuario almacenada en localStorage
        const userData = localStorage.getItem('userData');
        if (userData) {
            const userName = JSON.parse(userData).name;
            this.setProfileName(userName);
        } else {
            // Si no hay información de usuario, establecer el nombre en "login"
            this.setProfileName(null);
        }

    },
    items:[
        {
            id : 0,
            title: 'Inicio',
            icon: 'fa-house-user',
            onClick(router) {
                console.log('Inicio');
                router.push('/inicio');
            }
        },
        {
            id : 1,
            title: 'Información de Figuras',
            icon: 'fa-circle-info',
            onClick(router) {
                console.log('Información de Figuras');
                router.push('/info-figuras');
            }
        },
        {
            id : 2,
            title: 'Iniciativas/Agrupacións',
            icon: 'fa-people-group',
            onClick(router) {
                console.log('Iniciativas/Agrupacións');
                router.push('/iniciativas');
            }
        },
        {
            id : 3,
            title: 'Curiosidades',
            icon: 'fa-circle-question',
            onClick(router) {
                console.log('Curiosidades');
                router.push('/curiosidades');
            }
        },
        {
            id : 4,
            title: 'Arte e Astronomía',
            icon: 'fa-moon',
            onClick(router) {
                console.log('Arte e Astronomía');
                router.push('/arte-astronomia');
            }
        },
        {
            id : 5,
            title: 'Galería de Imaxes',
            icon: 'fa-images',
            onClick(router) {
                console.log('Galería de Imaxes');
                router.push('/galeria-imaxes');
            }
        }
    ]
}