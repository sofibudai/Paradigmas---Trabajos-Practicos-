module Vessel -- ( Vessel, newV, freeCellsV, updateV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantidad de bahias, la altura de las mismas y una ruta
newV bahias altura ruta = Ves (replicate bahias (newS altura)) ruta
 
freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves stacks _) = 0--sum length Ves (stacks _)

updateV :: [Stack] -> Route -> Container -> [Stack]
updateV (elemento:stacks) ruta contenedor | holdsS elemento contenedor ruta = (stackS elemento contenedor) : stacks
                                          | otherwise = elemento : (updateV stacks ruta contenedor)

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
loadV (Ves stacks ruta) contenedor = Ves (updateV stacks ruta contenedor) ruta
--holdS (stack contenedor ruta) = Ves (stackS stack contenedor) ruta

unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
-- descarga los contenedores que corresponden a la ciudad indicada y devuelve el barco sin esos contenedores
unloadV (Ves stacks ruta) ciudad = Ves stacks ruta
 
netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves stacks _) = sum (map netS stacks) 



