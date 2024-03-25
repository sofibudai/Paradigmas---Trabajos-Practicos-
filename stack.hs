module Stack ( Stack, newS, freeCellsS, stackS, netS, ultimaCiudad, holdsS, popS )
 where

import Container   
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)  

newS :: Int -> Stack                           
newS altura | altura > 0 = Sta [] altura

freeCellsS :: Stack -> Int                    
freeCellsS (Sta contenedores capacidad) = capacidad - length contenedores

stackS :: Stack -> Container -> Stack         
stackS (Sta contenedores capacidad) contenedor = Sta (contenedores ++ [contenedor]) capacidad
    
netS :: Stack -> Int                          
netS (Sta contenedores _) = sum (map netC contenedores)          

holdsS :: Stack -> Container -> Route -> Bool 
holdsS (Sta contenedores capacidad) contenedor ruta  
    | (freeCellsS (Sta contenedores capacidad) == capacidad) && (netS (Sta contenedores capacidad) + netC contenedor <= 20) = True
    | length contenedores + 1 > capacidad = False
    | netS (Sta contenedores capacidad) + netC contenedor > 20 = False
    | otherwise = inOrderR ruta (destinationC contenedor) (destinationC (last contenedores)) 


ultimaCiudad :: [Container] -> String        
ultimaCiudad contenedores = destinationC (last contenedores)   

popS :: Stack -> String -> Stack           
popS (Sta [] capacidad) _  = Sta [] capacidad
popS (Sta contenedores capacidad) ciudad 
    | ultimaCiudad contenedores == ciudad  = popS (Sta (init contenedores) capacidad) ciudad
    | otherwise = Sta contenedores capacidad


