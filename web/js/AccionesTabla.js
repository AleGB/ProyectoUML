/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var modificar = function (id, elem) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    document.getElementById(elem).setAttribute('href', '/ProyectoUML/PackageDiagramServlet?celda=' + celda + '&permiso=true');
};
var ver = function (id) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    window.open('/ProyectoUML/VerDiagramaServlet?celda=' + celda, "ventana1", "width=1100,height=600,scrollbars=YES,resizable=YES");
};
var eliminar = function (id, elem) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    document.getElementById(elem).setAttribute('href', '/ProyectoUML/EliminarDiagramaServlet?celda=' + celda);
};
var copiar = function (id, elem) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    document.getElementById(elem).setAttribute('href', '/ProyectoUML/CopiarDiagramaServlet?celda=' + celda);
};
function crearVisor(xml) {
    var xmlDoc = xml.responseXML;
    var doc = (new XMLSerializer()).serializeToString(xmlDoc);
    var xmlnode = (new DOMParser()).parseFromString(doc, 'text/xml');
    var packageDiagram = new UMLPackageDiagram({id: 'diagramaV', width: 1000, height: 580});
    packageDiagram.setName(xmlnode.getElementsByTagName("UMLPackageDiagram")[0].getAttributeNode("name").nodeValue);
    packageDiagram.setXML(xmlnode);
    packageDiagram.draw();
    packageDiagram.interaction(true);
    packageDiagram.setUpdateHeightCanvas(true);
    packageDiagram.setUpdateWidthCanvas(true);
}

function buscar(arch) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            crearVisor(this);
        }
    };
    xhr.open("GET", arch, true);
    xhr.send();
}

function cargarDiagrama(nombre) {
    var archivo = nombre + ".xml";
    buscar(archivo);
}
;