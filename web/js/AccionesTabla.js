/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var archivo;

var modificar = function (id, elem) {
    var celda = document.getElementById("elemento" + id).innerHTML;
    document.getElementById(elem).setAttribute('href', '/ProyectoUML/PackageDiagramServlet?celda='+celda+'&permiso=true');
};