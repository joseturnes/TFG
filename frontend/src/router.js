import { createRouter, createWebHistory } from 'vue-router';

import Home from './views/InfoFiguras.vue';
import StellariumWeb from "@/components/StellariumWeb.vue";
import InfoFiguras from "@/views/InfoFiguras.vue";
import IniciativasAgrupacions from "@/views/IniciativasAgrupacions.vue";
import Curiosidades from "@/views/Curiosidades.vue";
import ArteAstronomia from "@/views/ArteAstronomia.vue";
import GaleriaImaxes from "@/views/GaleriaImaxes.vue";

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
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;