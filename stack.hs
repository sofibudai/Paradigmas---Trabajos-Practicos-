module Stack ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)  -- int: cantidad de contenedores de un stack en particular 

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS  = Sta []

freeCellsS :: Stack -> Int                    -- responde las celdas disponibles en la pila
freeCellsS (Sta containers capacidad) = capacidad - length containers

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta containers capacidad) contenedor                     -- si la pila no esta llena, agrega el contenedor a la pila
    | length containers < capacidad = Sta (contenedor:containers) capacidad         
    | otherwise = error " El Stack esta lleno"                    -- si la pila esta llena, devuelve un error
    
netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta contenedores _) = sum (map netC contenedores)          

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta contenedores _) contenedor ruta = inOrderR ruta (destinationC contenedor) (destinationC (head contenedores))

ultimaCiudad :: [Container] -> String         -- funcion auxiliar que devuelve la ciudad correspondiente al contenedor que se ubica en el tope de la pila 
ultimaCiudad contenedores = destinationC (last contenedores)    -- usar Stack envez de [Container]

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada 
popS (Sta contenedores capacidad) ciudad | ultimaCiudad contenedores == ciudad = popS (Sta (init contenedores) capacidad) ciudad
                                         | otherwise = Sta contenedores capacidad 

-- popS :: Stack -> String -> Stack 
-- popS (Sta contenedores capacidad) ciudad | LastCity == ciudad = popS finalStack ciudad
--                                          | otherwise = Sta contenedores capacidad 
--     where 
--          lastCity = ultimaCiudad contenedores
--          finalStack = Sta (init contenedores) capacidad

