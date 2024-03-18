module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantidad de bahias, la altura de las mismas y una ruta
newV qBahias altura ruta = Ves 
 
freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco


loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco


unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad


netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco

