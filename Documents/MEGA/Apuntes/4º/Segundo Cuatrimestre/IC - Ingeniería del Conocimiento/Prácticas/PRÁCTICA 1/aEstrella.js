let columnas = 5;
let filas = 5;
let mapa;
let met;
let inici;
let listaAbierta;
let listaCerrada;

const direcciones = [
    [1, 1], 
    [1, 0],
    [1, -1],
    [0, 1],
    [0, -1],
    [-1, -1],
    [-1, 1],
    [-1, 0],
] 

function nodoMinimaF(lista) {
    indiceMinima = 0; //suponemos que está en el primero

    for (let i = 0; i < lista.length; i++) {
        if (lista[i].f < lista[indiceMinima].f) {
            indiceMinima = i;
        }
    }

    return lista[indiceMinima];
}

function eliminarDeArray(nodo, lista) {
    for (let i = lista.length - 1; i >= 0; i--) {
        if (lista[i] === nodo) {
            lista.splice(i, 1);
        }
    }
}

function distancia(nodo1, nodo2) {
    return Math.sqrt(
        Math.pow((nodo2.i - nodo1.i), 2)
        + Math.pow((nodo2.j - nodo1.j), 2));
}

// function busquedaArray(lista, elem) {
//     for (let i = 0; i < lista.length; i++) {
//         if (lista[i] === elem) {
//             return true;
//         }
//     }

//     return false;
// }

function nodo(i, j) {
    this.i = i;
    this.j = j;
    this.g = 0;
    this.h = 0;
    this.f = 0;
    this.hijos = [];
    this.padre;

    this.expandir = function () {
        let i = this.i;
        let j = this.j;

        direcciones.forEach( direccion => {
            
            let x = i + direccion[0];
            let y = j + direccion[1];


            if (x >= 0 && x < columnas
                && y >= 0 && y < filas)
            {
                if(mapa[x][y] == undefined) {
                    mapa[x][y] = new nodo(x, y)
                }

                this.hijos.push(mapa[x][y]);
            }
        })

        // falta comprobar que no son obstaculos o padre
        
    }

}

function inicializarMapa(c, f, p_i, p_m) {
    listaAbierta = [];
    listaCerrada = [];
    inicio_i = p_i[1]; //me las pasa al reves
    inicio_j = p_i[0]; //me las pasa al reves
    fin_i = p_m[0];
    fin_j = p_m[1];
    columnas = c;
    filas = f;
    mapa = new Array(columnas);
    for (let i = 0; i < mapa.length; i++) {
        mapa[i] = new Array(filas);
    }

    //creo el nodo inicial
    inici = new nodo(inicio_i, inicio_j);
    mapa[inicio_i][inicio_j] = inici;

    //creo el nodo meta
    met = new nodo(fin_i, fin_j);
    mapa[fin_i][fin_j] = met;
    
    //añado el nodo inicial a listaAbierta
    listaAbierta.push(inici);
    
    //añado h y f al nodo inicio
    inici.h = distancia(inici, met);
    inici.f = inici.g + inici.h;

}

function buscarCamino() {
    let fallo = false;
    let fin = false;
    let n_actual;

    while (!(fallo || fin)) {

        if (listaAbierta.length <= 0) {
            fallo = true;
        }

        n_actual = nodoMinimaF(listaAbierta);
        eliminarDeArray(n_actual, listaAbierta);

        listaCerrada.push(n_actual);

        if (n_actual === met) {
            fin = true;
        }

        n_actual.expandir();

        n_actual.hijos.forEach(hijo => {
            
            let nuevaDistancia = n_actual.g + distancia(n_actual, hijo);

            if (listaAbierta.includes(hijo)) {
                if (nuevaDistancia < hijo.g) {
                    hijo.g = nuevaDistancia;
                    hijo.f = hijo.g + hijo.h;
                    hijo.padre = n_actual;
                }

            } else if (listaCerrada.includes(hijo)) {
                if (nuevaDistancia < hijo.g) {
                    //cambiar la g, el padre y hacer recorrido en profundidad de sus hijos
                }

            } else { //no está ni en abierta ni en cerrada
                hijo.g = n_actual.g + distancia(n_actual, hijo);
                hijo.h = distancia(hijo, met);
                hijo.f = hijo.g + hijo.h;
                hijo.padre = n_actual;
                listaAbierta.push(hijo);
            }
        })
    
        
        // console.log(mapa);
        // console.log(m);
        // console.log(listaAbierta);
        // console.log(listaCerrada);

    }

    if(fin){
        let solucion = [];
        let actual = listaCerrada[listaCerrada.length - 1];
        let padre = actual.padre;

        while(padre) {
            actual = padre;
            solucion.push({
                x: actual.i,
                y: actual.j
            });
            padre = actual.padre;
        }

        solucion.pop(); //No nos interesa el nodo de inicio.
        solucion.reverse(); //Para dibujar el camino, del principio

        return solucion;
    }

    /*console.log("metaaaaaaaaaaa");
    console.log(met);*/

}