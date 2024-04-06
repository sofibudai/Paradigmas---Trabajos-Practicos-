{-BuqueTruck

modelamos el buque de una compañia naviera de contenedores
El buque tiene una ruta establecida y una cantidad de bahias para estiba.
Todas las bahias toleran la misma cantidad de contenedores apilados.

Al momento de llegar a uno de los destinos de su ruta tiene que poder descargar los contenedores con ese destino simplemente desapilando.
Con lo cual al momento de cargar no puede apilarse un contenedor con destino posterior al que le queda de bajo.
La carga de un contenedor se hace solo si hay una bahia que lo acepte. 

Los contenedores tienen destino y peso en toneladas.
Una bahia no tolera apilar mas de 20 toneladas.

Al llegar a puerto el barco descarga todos los contenedores con ese destino. 
luego puede cargar mas contenedores, 
y luego queda listo para partir al siguiente destino.

Para sostener este modelo se cuenta con las siguientes entidades:
(nada de esto se puede modificar)-}

module Container ( Container, newC, destinationC, netC )
 where

data Container = Con String Int deriving (Eq, Show)

newC :: String -> Int -> Container   -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
destinationC :: Container -> String  -- responde la ciuda destino del contenedor
netC :: Container -> Int             -- responde el peso en toneladas del contenedor

------------------------
module Route ( Route, newR, inOrderR )
 where

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta

------------------------
module Stack ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada

------------------------
module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantidad de bahias, la altura de las mismas y una ruta  (altura = ciudad ?)
freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco

