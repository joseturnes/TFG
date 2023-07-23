export default {
    profile: {
        name : 'Iniciar sesión como administrador',
        img: 'stellarium.jpeg',
        onClick(router){
            if(localStorage.getItem('userToken')){
                router.push('/usuario')
            } else {
                router.push('/administracion');
            }

        }
    },
    logout: {
        name : 'Pechar sesión',
        img: 'stellarium.jpeg',
        onClick(router){
            localStorage.clear();
            window.location.reload();
        }
    },
    setProfileName(userName) {
        if (userName) {
            this.profile.name = userName;
        } else {
            this.profile.name = 'Iniciar sesión';
        }
    },
    created() {
        const userData = localStorage.getItem('userData');
        if (userData) {
            const userName = JSON.parse(userData).name;
            this.setProfileName(userName);
        } else {
            this.setProfileName(null);
        }

    },
    items:[
        {
            id : 0,
            title: 'Inicio',
            icon: 'fa-house-user',
            onClick(router) {
                router.push('/inicio');
            }
        },
        {
            id : 1,
            title: 'Información de Figuras',
            icon: 'fa-circle-info',
            onClick(router) {
                router.push('/info-figuras');
            }
        },
        {
            id : 2,
            title: 'Iniciativas/Agrupacións',
            icon: 'fa-people-group',
            onClick(router) {
                router.push('/iniciativas');
            }
        },
        {
            id : 3,
            title: 'Curiosidades',
            icon: 'fa-circle-question',
            onClick(router) {
                router.push('/curiosidades');
            }
        },
        {
            id : 4,
            title: 'Arte e Astronomía',
            icon: 'fa-moon',
            onClick(router) {
                router.push('/arte-astronomia');
            }
        },
        {
            id : 5,
            title: 'Galería de Imaxes',
            icon: 'fa-images',
            onClick(router) {
                router.push('/galeria-imaxes');
            }
        }
    ]
}