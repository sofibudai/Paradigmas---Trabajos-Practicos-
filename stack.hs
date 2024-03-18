module Stack ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)  -- int: cantidad de contenedores de un stack en particular 

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS = Sta                                    -- [] ?

freeCellsS :: Stack -> Int                    -- responde las celdas disponibles en la pila
freeCellsS = cantmax - Sta (_ cantContenedores) 

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS = Sta ([contenedor] _) (++)            -- aclaramos la variable??  stackS (Sta contenedores capacidadStack) contenedor = Sta ((++) contenedores [contenedor]) capacidadStack

netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS = sum (map netC contenedores)            -- netS (Sta contenedores _) = sum (map netC contenedores)

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS =  

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada 

