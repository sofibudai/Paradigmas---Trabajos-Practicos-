module Route ( Route, newR, inOrderR )
    where

import Data.List (elemIndex)

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades                  
newR ciudades
    | null ciudades = error "La ruta ingresada no tiene ningun destino"
    | otherwise = Rou ciudades

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta
inOrderR (Rou ciudades) ciudad1 ciudad2 = elemIndex ciudad1 ciudades <= elemIndex ciudad2 ciudades  


-- manejar errores en inOrderR ?