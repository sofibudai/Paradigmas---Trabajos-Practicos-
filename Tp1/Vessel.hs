module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantidad de bahias, la altura de las mismas y una ruta
newV = Ves 
 
freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves stacks _) = length Ves (stacks _)

updateV :: [Stack] -> Route -> Container -> Stack
updateV (elemento:stacks) ruta contenedor | holdsS elemento ruta contenedor = (stackS elemento contenedor) : stacks
                                          | otherwise = elemento : (updateV stacks ruta contenedor)

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
loadV (Ves ss ruta) contenedor = Ves (updateV ss ruta contenedor) ruta
--holdS (stack contenedor ruta) = Ves (stackS stack contenedor) ruta

unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
-- descarga los contenedores que corresponden a la ciudad indicada y devuelve el barco sin esos contenedores
unloadV 

netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves stacks _) = sum (map netS stacks)



