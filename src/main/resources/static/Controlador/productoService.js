function crearInput($form,name,tipo,placeholder){
    $form.insertAdjacentHTML("beforeend",`<input type=${tipo} class="borrar form-control w-50" name=${name} placeholder=${placeholder} required>`)
}

function crearCheckbox($form,name,textoInformativoCampo){
    $form.insertAdjacentHTML("beforeend", `
                                <label class="borrar text-white form-check-label">${textoInformativoCampo}</label>
                                <input type="checkbox" class="borrar form-check-input" name=${name} required>
                            `)
}

function crearSelectorAroma($form,name){

    $form.insertAdjacentHTML("beforeend",`<select class="borrar form-control w-50" name=${name} required>
                                <option value="SIN_AROMA">SIN AROMA</option>
                                <option value="VAINILLA">VAINILLA</option>
                                <option value="COCO">COCO</option>
                        </select>`)
}

function crearSelectorMedicamento($form,name){

    $form.insertAdjacentHTML("beforeend",`<select class="borrar form-control w-50" name=${name} required>
                                <option value="ANALGESICOS">ANALGESICOS</option>
                                <option value="ANTIBIOTICOS">ANTIBIOTICOS</option>
                                <option value="ANTIDEPRESIVOS">ANTIDEPRESIVOS</option>
                                <option value="ANTIINFLAMATORIOS">ANTIINFLAMATORIOS</option>
                        </select>`)
}

function crearBotonEnviar($form){
    $form.insertAdjacentHTML("beforeend",`<button class="borrar btn btn-light w-25" type="submit">Enviar</button>`)

}

function borrar($form){
    $form.querySelectorAll(".borrar").forEach(input => {
        input.remove()
    });
}

function insertarInputsEnFormulario(valorFormulario,$form){
    borrar($form)

    console.log("Actualizando")
    console.log($form)

    if(valorFormulario==="HIGIENE"){
        $form.setAttribute("action","api/ingresarHigiene")
        crearSelectorAroma($form,"aroma",)
        crearCheckbox($form,"hipoalergenico","Es hipoalergenico")
        crearBotonEnviar($form)
    }

    if(valorFormulario==="VACUNA"){
        $form.setAttribute("action","api/ingresarVacuna")
        crearInput($form,"cantAplicaciones","number","Cantidad de aplicaciones")
        crearBotonEnviar($form)
    }

    if(valorFormulario==="MEDICAMENTO"){
        $form.setAttribute("action","api/ingresarMedicamento")
        crearSelectorMedicamento($form,"tipo")
        crearInput($form,"dosis","number","Dosis del medicamento")
        crearBotonEnviar($form)
    }
    
}

export {insertarInputsEnFormulario}
    











