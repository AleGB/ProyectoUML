/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var XlM1;
var usepackageDiagram;
var div = document.getElementById("usePackageDiagram");
var doc;
//inicializacion

function extraerXML(xml) {
    var xmlDoc = xml.responseXML;
    doc = (new XMLSerializer()).serializeToString(xmlDoc);
    var xmlnode = (new DOMParser()).parseFromString(doc, 'text/xml');
    usepackageDiagram = new UMLPackageDiagram({id: 'usePackageDiagram', width: 1000, height: 580});
    usepackageDiagram.setName(xmlnode.getElementsByTagName("UMLPackageDiagram")[0].getAttributeNode("name").nodeValue);
    document.getElementById("nameDiagram").innerHTML = usepackageDiagram.getName();
    usepackageDiagram.setXML(xmlnode);
    usepackageDiagram.draw();
    usepackageDiagram.interaction(true);
    usepackageDiagram.setUpdateHeightCanvas(true);
    usepackageDiagram.setUpdateWidthCanvas(true);
}

function peticion(arch) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            extraerXML(this);
        }
    };
    xhr.open("GET", arch, true);
    xhr.send();
}
;
function load(permiso, nombre) {
    if (permiso === false) {
        usepackageDiagram = new UMLPackageDiagram({id: 'usePackageDiagram', width: 1000, height: 580});
        usepackageDiagram.setName("Package Diagram");
        document.getElementById("nameDiagram").innerHTML = usepackageDiagram.getName();
        usepackageDiagram.draw();
        usepackageDiagram.interaction(true);
        usepackageDiagram.setUpdateHeightCanvas(true);
        usepackageDiagram.setUpdateWidthCanvas(true);
    } else {
        var archivo = nombre+".xml";
        peticion(archivo);
    }
}
;

//Codigo para agregar elementos
var interaccionUnClick = function (f) {
    usepackageDiagram.interaction(false);
    var funcionCaptura = function (event) {
        var mousex = event.pageX - div.offsetLeft;
        var mousey = event.pageY - div.offsetTop;
        f(usepackageDiagram, mousex, mousey);
        div.onclick = false;
        usepackageDiagram.draw();
        usepackageDiagram.interaction(true);
    };
    div.onclick = funcionCaptura;
};

document.getElementById("btnPackage").onclick = function () {
    function f(d, x, y) {
        d.addElement(new UMLPackage({x: x, y: y}));
        //XlM1 = d.getXMLString();
        //arreglo.push(XlM1);

    }
    interaccionUnClick(f);
};

document.getElementById("btnPackageContainer").onclick = function () {
    function f(d, x, y) {
        d.addElement(new UMLPackageContainer({x: x, y: y}));
        //XlM1 = d.getXMLString();
        //arreglo.push(XlM1);
    }
    interaccionUnClick(f);
};

document.getElementById("btnNote").onclick = function () {
    function f(d, x, y) {
        d.addElement(new UMLNote({x: x, y: y}));
        //XlM1 = d.getXMLString();
        //arreglo.push(XlM1); 
    }
    interaccionUnClick(f);
};
//Codigo para relaciones
var interaccionDoubleClick = function (fdouble) {
    usepackageDiagram.interaction(false);
    var primer = function (event) {
        var x1 = event.pageX - div.offsetLeft;
        var y1 = event.pageY - div.offsetTop;
        var elem1 = usepackageDiagram.getElementByPoint(x1, y1);
        if (elem1 && elem1 instanceof Element) {
            div.onclick = false;
            var segundo = function (event) {
                var x2 = event.pageX - div.offsetLeft;
                var y2 = event.pageY - div.offsetTop;
                var elem2 = usepackageDiagram.getElementByPoint(x2, y2);
                if (elem2 && elem2 instanceof Element) {
                    fdouble(usepackageDiagram, elem1, elem2);
                    div.onclick = false;
                    usepackageDiagram.draw();
                    usepackageDiagram.interaction(true);
                }
            };
            div.onclick = segundo;
        }
    };
    div.onclick = primer;
};
document.getElementById("btnDependecy").onclick = function () {
    function fdouble(d, a, b) {
        d.addElement(new UMLDependency({a: a, b: b}));
        //XlM1 = d.getXMLString();
    }
    interaccionDoubleClick(fdouble);
};
document.getElementById("btnMerge").onclick = function () {
    function fdouble(d, a, b) {
        d.addElement(new UMLPackageMerge({a: a, b: b}));
        //XlM1 = d.getXMLString();
        //return d;
    }
    interaccionDoubleClick(fdouble);
};
document.getElementById("btnPublicImport").onclick = function () {
    function fdouble(d, a, b) {
        d.addElement(new UMLPackagePublicImport({a: a, b: b}));
        //return d;
        //XlM1 = d.getXMLString();
    }
    interaccionDoubleClick(fdouble);
};
document.getElementById("btnPrivateImport").onclick = function () {
    function fdouble(d, a, b) {
        d.addElement(new UMLPackagePrivateImport({a: a, b: b}));
        //return d;
        //XlM1 = d.getXMLString();
    }
    interaccionDoubleClick(fdouble);
};
document.getElementById("btnLine").onclick = function () {
    function fdouble(d, a, b) {
        d.addElement(new UMLLine({a: a, b: b}));
        //return d;
        //XlM1 = d.getXMLString();
    }
    interaccionDoubleClick(fdouble);
};
//Codigo para cambiar nombre del diagrama
var fn = function (value) {
    if (value === '')
        value = 'Untitled';
    usepackageDiagram.setName(value);

    document.getElementById("nameDiagram").innerHTML = usepackageDiagram.getName();

    usepackageDiagram.draw();
};

document.getElementById("nameDiagram").onclick = function () {
    var dialog = new Dialog({text: 'Diagram name: ', cancelable: true});
    dialog.show(fn, usepackageDiagram.getName());
};

//Borrar elemento
var borrar = function () {
    var seleccion = function (event) {
        var x = event.pageX - div.offsetLeft;
        var y = event.pageY - div.offsetTop;
        var elem = usepackageDiagram.getElementByPoint(x, y);
        if (elem && elem instanceof Element) {
            var text = 'Do you want to delete the object' + elem.getType() + '?';
            //Create the text box
            var dialog = new Dialog({text: text, cancelable: true});
            //Function that changes the name of the diagram
            var fb = function () {
                usepackageDiagram.delElement(elem);
                usepackageDiagram.draw();
                div.onclick = false
            };
            //Show the text box in the web page
            dialog.show(fb);
        }
    };
    div.onclick = seleccion;
    ;
};
document.getElementById("btnBorrar").onclick = function () {
    borrar();
};

//regresar
document.getElementById("btnRegresar").onclick = function () {
    location.href = "/DPServlet";
};


/*document.getElementById("btnSave").onclick = function() {
 var fso  = ActiveXObject("Scripting.FileSystemObject"); 
 var fh = fso.CreateTextFile("c:\\Test.txt", true); 
 fh.WriteLine(XlM1); 
 fh.Close(); 
 };*/

document.getElementById("btnSave").onclick = function () {
    var xml = (new DOMParser()).parseFromString('<diagramaP/>', 'text/xml');
    var app = xml.getElementsByTagName('diagramaP')[0];
    app.appendChild(usepackageDiagram.getXML(xml));
    var txt = (new XMLSerializer()).serializeToString(xml);

    console.log(xml);
    var textFileAsBlob = new Blob([txt], {type: 'text/plain'});
    var fileNameToSaveAs = "myNewFile.txt";
    var downloadLink = document.createElement("a");
    downloadLink.download = fileNameToSaveAs;
    downloadLink.innerHTML = "My Hidden Link";
    window.URL = window.URL || window.webkitURL;
    downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
    downloadLink.onclick = destroyClickedElement;
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);

    downloadLink.click();
};

function destroyClickedElement(event)
{
    document.body.removeChild(event.target);
}