import Stack 
import Route 
--import Vessel
import Data.List (elemIndex)
import Container 
import Vessel

import Control.Exception
import System.IO.Unsafe


{- bS = newV 1 1 rutaLarga
bChato = newV 2 1 rutaLarga
bChatoX = loadV bChato cMdq
bAlto = newV 1 2 rutaLarga
bAltoX = loadV bAlto cMdq -}


pesoNeto = netS sXX

holdStackVacio = holdsS sLL cMdq rutaLarga

stackReventado = holdsS sXX cQeq rutaLarga

vesVacio = newV 0 0 rutaLarga  -- barco con ningun lugar
vesDos = newV 2 2 rutaLarga    -- barco con 2 bahias de altura 2. 4 celdas disponibles

vesCargado = loadV vesDos cMdq
vesCargado2 = loadV vesCargado cMdq
vesCargado3 = loadV vesCargado2 cMdq
vesCargado4 = loadV vesCargado3 cMdq
vesCargado5 = loadV vesCargado4 cMdq
stackAlturaNegativo = newS (-1) 


{- t = [ destinationC cMdq == "MDQ", -- "C1 destino de un contenedor"      -- hacer nuestros propios test 
      inOrderR rutaLarga bhi qeq, -- "R1 enOrden"
      inOrderR rutaLarga bhi rsl, -- "R2 enOrden"
      inOrderR rutaLarga qeq mdq, -- "R3 enOrden"
      inOrderR rutaLarga qeq rsl, -- "R4 enOrden"
      netV vesVacio == 0,
      freeCellsV vesDos == 4, 
      netV vesVacio == 0,

      testF cVacio,
      testF rutaVacia,
      testF noHold,               -- no puede dar error. da siempre o true o false
      True ] -}

      
-------------------------------------------------------

mdq = "MDQ"
rsl = "RSL"
bhi = "BHI"
bue = "BUE"
qeq = "QEQ"
mvd = "MVD"

rutaLarga = newR [ bhi, qeq, mdq, bue, rsl ]
rutaVacia = newR []

cVacio = newC bue 0
cNegativo = newC bue (-2)

rVacia = newR []

sCeroAlt = newS 0
sAltNeg = newS (-1)

cMdq = newC mdq 5
cBue = newC bue 7
cQeq = newC qeq 9

cMdq16 = newC mdq 16

sLL = newS 3           -- pila
sXL = stackS sLL cMdq  -- stack
sXX = stackS sXL cMdq
sPasado = stackS sXX cMdq

stackSobrePeso = holdsS sXL cMdq16 rutaLarga
stackDisponible = holdsS sXL cMdq rutaLarga


t = [ destinationC cMdq == "MDQ",
      netC cBue == 7,
      stackDisponible == True, 
      stackSobrePeso == False,
      inOrderR rutaLarga qeq bhi == True,
      inOrderR rutaLarga bhi qeq == False, 
      freeCells sXL == 2,

      testF cVacio, 
      testF cNegativo, 
      testF rVacia, 
      testF sCeroAlt,
      testF sAltNeg,      
    True ]


testF :: Show a => a -> Bool                          -- cambiar la funcion para ver como probamos nosotros test for failure: ponerle cosas que van a dar error 
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just () 

-- testear holds cuando no hay contenedores y ademas hacer que solo admita que el primer contenedor sea del destino que esta ultimo en la lista