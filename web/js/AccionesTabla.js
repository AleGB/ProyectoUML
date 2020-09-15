/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var packageDiagram;
var modificar = function (id, elem) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    document.getElementById(elem).setAttribute('href', '/ProyectoUML/PackageDiagramServlet?celda=' + celda + '&permiso=true');
};
var ver = function (id, elem) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    window.open('/ProyectoUML/VerDiagramaServlet?celda=' + celda, "ventana1","width=1100,height=600,scrollbars=YES,resizable=YES");
};

function crearVisor(xml) {
    var xmlDoc = xml.responseXML;
    var doc = (new XMLSerializer()).serializeToString(xmlDoc);
    alert(doc);
    var xmlnode = (new DOMParser()).parseFromString(doc, 'text/xml');
    packageDiagram = new UMLPackageDiagram({id: 'divDiagrama', width: 1000, height: 580});
    packageDiagram.setName(xmlnode.getElementsByTagName("UMLPackageDiagram")[0].getAttributeNode("name").nodeValue);
    //document.getElementById("nameDiagram").innerHTML = usepackageDiagram.getName();
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
            alert("entre a buscar");
            crearVisor(this);
        }
    };
    xhr.open("GET", arch, true);
    xhr.send();
}

function cargarDiagrama(nombre) {
    alert(nombre);
    var archivo = nombre + ".xml";
    alert(archivo);
    buscar(archivo);
}
;