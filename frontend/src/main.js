import { createApp } from 'vue'
import App from './App.vue'

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import {
    faUserSecret,
    faUser,
    faBars,
    faFire,
    faHomeUser,
    faScrewdriverWrench,
    faCircleInfo,
    faPeopleGroup,
    faCircleQuestion,
    faMoon,
    faImages,
    faArrowRightFromBracket,
    faSpinner,
    faXmark,
    faTrashCan
} from '@fortawesome/free-solid-svg-icons'

import 'bootstrap/dist/css/bootstrap.min.css';
import './main.css'
import router from "@/router";

/* add icons to the library */
library.add(faUserSecret);
library.add(faBars);
library.add(faFire);
library.add(faHomeUser);
library.add(faScrewdriverWrench);
library.add(faCircleInfo);
library.add(faPeopleGroup);
library.add(faCircleQuestion);
library.add(faMoon);
library.add(faImages);
library.add(faUser);
library.add(faArrowRightFromBracket);
library.add(faSpinner);
library.add(faXmark);
library.add(faTrashCan);

createApp(App)
    .use(router)
    .component('font-awesome-icon', FontAwesomeIcon)
    .mount('#app')
