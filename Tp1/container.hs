module Container ( Container, newC, destinationC, netC )
 where

data Container = Con String Int deriving (Eq, Show)

newC :: String -> Int -> Container   
newC ciudad peso | peso > 0 = Con ciudad peso

destinationC :: Container -> String 
destinationC (Con ciudad _) = ciudad 

netC :: Container -> Int            
netC (Con _ peso) = peso

