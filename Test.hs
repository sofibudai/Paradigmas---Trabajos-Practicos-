import Stack 
import Route 
--import Vessel
import Data.List (elemIndex)
import Container 

import Control.Exception
import System.IO.Unsafe



mdq = "MDQ"
rsl = "RSL"
bhi = "BHI"
bue = "BUE"
qeq = "QEQ"
mvd = "MVD"


rutaLarga = newR [ bhi, qeq, mdq, bue, rsl ]

rutaVacia = newR []

{- bS = newV 1 1 rutaLarga
bChato = newV 2 1 rutaLarga
bChatoX = loadV bChato cMdq
bAlto = newV 1 2 rutaLarga
bAltoX = loadV bAlto cMdq -}

cVacio = newC bue 0
cMdq = newC mdq 5
cBue = newC bue 7
cQeq = newC qeq 9

sLL = newS 2          -- pila
sXL = stackS sLL cMdq  -- stack
sXX = stackS sXL cMdq
sPasado = stackS sXX cMdq

pesoNeto = netS sXX

noHold = holdsS sXL cBue rutaLarga
holdStackVacio = holdsS sLL cMdq rutaLarga

stackReventado = holdsS sXX cQeq rutaLarga

t = [ destinationC cMdq == "MDQ", -- "C1 destino de un contenedor"
      inOrderR rutaLarga bhi qeq, -- "R1 enOrden"
      inOrderR rutaLarga bhi rsl, -- "R2 enOrden"
      inOrderR rutaLarga qeq mdq, -- "R3 enOrden"
      inOrderR rutaLarga qeq rsl, -- "R4 enOrden"
      testF sPasado,    -- "S4 una pila no tolera mas que su capacidad"

      testF cVacio,
      testF rutaVacia,
      testF noHold,
      True ]

testF :: Show a => a -> Bool                          -- test for failure: ponerle cosas que van a dar error 
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

-- testear holds cuando no hay contenedores y ademas hacer que solo admita que el primer contenedor sea del destino que esta ultimo en la lista