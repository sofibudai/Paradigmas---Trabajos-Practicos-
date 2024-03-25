module Stack ( Stack, newS, freeCellsS, stackS, netS, ultimaCiudad, holdsS, popS )
 where

import Container   
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)  -- int: cantidad de contenedores de un stack en particular 

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS altura | altura > 0 = Sta [] altura

freeCellsS :: Stack -> Int                    -- responde las celdas disponibles en la pila
freeCellsS (Sta contenedores capacidad) = capacidad - length contenedores

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta contenedores capacidad) contenedor = Sta (contenedores ++ [contenedor]) capacidad
    
netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta contenedores _) = sum (map netC contenedores)          

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta contenedores capacidad) contenedor ruta  
    | (freeCellsS (Sta contenedores capacidad) == capacidad) && (netS (Sta contenedores capacidad) + netC contenedor <= 20) = True
    | length contenedores + 1 > capacidad = False
    | netS (Sta contenedores capacidad) + netC contenedor > 20 = False
    | otherwise = inOrderR ruta (destinationC contenedor) (destinationC (last contenedores)) 

{- holdsS :: Stack -> Container -> Route -> Bool
holdsS (Sta contenedores capacidad) contenedor ruta =
    tieneEspacio && tienePeso && estaEnOrden
    where
        cantContenedores = length contenedores
        espacioDisponible = freeCellsS (Sta contenedores capacidad)
        espacioRestante = capacidad - cantContenedores
        sumaPeso = netS (Sta contenedores capacidad) + netC contenedor
        tieneEspacio = espacioDisponible == capacidad && sumaPeso <= 20
        tienePeso = cantContenedores + 1 <= capacidad && sumaPeso <= 20
        estaEnOrden = inOrderR ruta (destinationC contenedor) (destinationC (last contenedores))
 -}

ultimaCiudad :: [Container] -> String         -- funcion auxiliar que responde la ciudad correspondiente al contenedor que se ubica en el tope de la pila 
ultimaCiudad contenedores = destinationC (last contenedores)   

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada 
popS (Sta [] capacidad) _  = Sta [] capacidad
popS (Sta contenedores capacidad) ciudad 
    | ultimaCiudad contenedores == ciudad  = popS (Sta (init contenedores) capacidad) ciudad
    | otherwise = Sta contenedores capacidad


