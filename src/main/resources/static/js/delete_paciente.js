function deleteBy(id) {

    let url = "/pacientes/" + id;

    let payload = {
        method: "DELETE"
    }

    fetch(url, payload)
    
    .then(response => response.json())
    
    .then(data => {
        // muestro por consola el paciente eliminado
        console.log("Paciente eliminado: " + data)
        // muestro en un cartel que fue eliminado
        alert("Paciente eliminado");
        // elimino la fila de la tabla
        let filaSeleccionada = document.querySelector("#tr_"+ id);
        filaSeleccionada.remove()
    })
    
    .catch(error => alert(error))

}