import {insertarInputsEnFormulario} from "../../Controlador/productoService.js"

const $dom = document;
const $selectorTipoProducto = $dom.querySelector("#tipo-producto");
const $form = $dom.querySelector("form")

$selectorTipoProducto.addEventListener("click",(e)=>{
    insertarInputsEnFormulario(e.target.value,$form)
})
