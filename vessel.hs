module Vessel -- ( Vessel, newV, freeCellsV, updateV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  
newV bahias altura ruta = Ves (replicate bahias (newS altura)) ruta

freeCellsV :: Vessel -> Int          
freeCellsV (Ves stacks _) = sum (map freeCellsS stacks)

updateV :: [Stack] -> Route -> Container -> [Stack]
updateV [] _ _ = []
updateV (elemento:stacks) ruta contenedor
    | holdsS elemento contenedor ruta = (stackS elemento contenedor) : stacks
    | otherwise = elemento : (updateV stacks ruta contenedor)

loadV :: Vessel -> Container -> Vessel
loadV (Ves stacks ruta) contenedor = Ves (updateV stacks ruta contenedor) ruta

unloadV :: Vessel -> String -> Vessel  
unloadV (Ves stacks ruta) ciudad = Ves (map (`popS` ciudad) stacks) ruta

netV :: Vessel -> Int                 
netV (Ves stacks _) = sum (map netS stacks)






