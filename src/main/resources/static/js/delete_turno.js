function deleteBy(id) {

    let url = "/turnos/" + id;

    let payload = {
        method: "DELETE"
    }

    fetch(url, payload)
    
    .then(response => response.json())
    
    .then(data => {

        if (data.mensaje) {alert(data.mensaje)}

        else {
            // muestro por consola el paciente eliminado
            console.log("Turno eliminado: " + data)
            // muestro en un cartel que fue eliminado
            alert("Turno eliminado");
            // elimino la fila de la tabla
            let filaSeleccionada = document.querySelector("#row_"+ id);
            filaSeleccionada.remove()
        }
    
    })
    
    .catch(error => alert(error))

}