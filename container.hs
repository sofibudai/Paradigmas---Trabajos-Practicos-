module Container ( Container, newC, destinationC, netC )
 where
import Text.PrettyPrint (TextDetails(Str))

data Container = Con String Int deriving (Eq, Show)

newC :: String -> Int -> Container   -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
newC ciudad peso | peso > 0 = Con ciudad peso

destinationC :: Container -> String  -- responde la ciuda destino del contenedor
destinationC (Con ciudad _) = ciudad 

netC :: Container -> Int             -- responde el peso en toneladas del contenedor
netC (Con _ peso) = peso

