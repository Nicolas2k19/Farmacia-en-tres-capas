import {eliminarFarmacia} from "../Acceso/farmaciaAcceso.js"


const eliminar = async (id)=>{
    return await eliminarFarmacia(id);
}

export {eliminar}