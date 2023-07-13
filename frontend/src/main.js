import { createApp } from 'vue'
import App from './App.vue'

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { faUserSecret, faBars, faFire, faHomeUser, faScrewdriverWrench, faCircleInfo, faPeopleGroup, faCircleQuestion, faMoon, faImages } from '@fortawesome/free-solid-svg-icons'

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

createApp(App)
    .component('font-awesome-icon', FontAwesomeIcon)
    .mount('#app')
