import {eliminar} from "../../Controlador/farmaciaControlador.js"


async function eliminarFarmacia(id){
    await eliminar(id)
    location.reload()
}


const anularComportamientoDefault = (() => {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})


anularComportamientoDefault();



window.eliminarFarmacia = eliminarFarmacia;



