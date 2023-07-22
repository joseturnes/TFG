import { createRouter, createWebHistory } from 'vue-router';

import StellariumWeb from "@/components/StellariumWeb.vue";
import InfoFiguras from "@/views/InfoFiguras.vue";
import IniciativasAgrupacions from "@/views/IniciativasAgrupacions.vue";
import Curiosidades from "@/views/Curiosidades.vue";
import ArteAstronomia from "@/views/ArteAstronomia.vue";
import GaleriaImaxes from "@/views/GaleriaImaxes.vue";
import Login from "@/views/Login.vue";
import Usuario from "@/views/Usuario.vue";

const routes = [
    {
        path: '/inicio',
        name: 'Inicio',
        component: StellariumWeb
    },
    {
        path: '/',
        name: '/',
        component: StellariumWeb
    },
    {
        path: '/info-figuras',
        name: 'InfoFiguras',
        component: InfoFiguras
    },
    {
        path: '/iniciativas',
        name: 'IniciativasAgrupacions',
        component: IniciativasAgrupacions
    },
    {
        path: '/curiosidades',
        name: 'Curiosidades',
        component: Curiosidades
    },
    {
        path: '/arte-astronomia',
        name: 'ArteAstronomia',
        component: ArteAstronomia
    },
    {
        path: '/galeria-imaxes',
        name: 'GaleriaImaxes',
        component: GaleriaImaxes
    },
    {
        path: '/administracion',
        name: 'Administracion',
        component: Login
    },
    {
        path: '/usuario',
        name: 'Usuario',
        component: Usuario
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth && !localStorage.getItem('userToken')) {
        // Redirigir al componente Login si no está logueado
        next('/administracion');
    } else {
        next();
    }
});

export default router;