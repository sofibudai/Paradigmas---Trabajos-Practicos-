module Route ( Route, newR, inOrderR )
    where

import Data.List (elemIndex)

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    
newR ciudades | length ciudades > 0 = Rou ciudades

inOrderR :: Route -> String -> String -> Bool  
inOrderR (Rou ciudades) ciudad1 ciudad2 = elemIndex ciudad1 ciudades <= elemIndex ciudad2 ciudades  


