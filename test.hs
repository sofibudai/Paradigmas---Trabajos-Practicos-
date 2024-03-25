import Stack 
import Route 
import Container 
import Vessel

import Control.Exception
import System.IO.Unsafe
import Data.List (elemIndex)


bS = newV 1 1 rutaLarga
bChato = newV 2 1 rutaLarga
bChatoX = loadV bChato cMdq
bAlto = newV 1 2 rutaLarga
bAltoX = loadV bAlto cMdq 

holdStackVacio = holdsS sLL cMdq rutaLarga

stackReventado = holdsS sXX cQeq rutaLarga

stackAlturaNegativo = newS (-1) 

      
----------------------------------------------------------------------------------------------------------------------------------------------------

mdq = "MDQ"
rsl = "RSL"
bhi = "BHI"
bue = "BUE"
qeq = "QEQ"
mvd = "MVD"

cMdq = newC mdq 5
cBue = newC bue 7
cQeq = newC qeq 9
cBhi = newC bhi 3
cMdq16 = newC mdq 16

sLL = newS 3           -- pila
sXL = stackS sLL cMdq  -- stack
sXX = stackS sXL cMdq
sSinLugar = stackS sXX cMdq

sV0 = newS 2
sV1 = stackS sV0 cBue
sV2 = stackS sV1 cQeq

rutaLarga = newR [ bhi, qeq, mdq, bue, rsl ]     -- ordenada del primer destino al ultimo de izq a derecha 
rutaVacia = newR []

cVacio = newC bue 0
cNegativo = newC bue (-2)

pesoNetoC = netC cBue

rVacia = newR []

sCeroAlt = newS 0
sAltNeg = newS (-1)

rutasEnOrden = inOrderR rutaLarga bhi qeq
rutasNoEnOrden = inOrderR rutaLarga qeq bhi

celdasLibresStack = freeCellsS sXL 
stackSinLugar = freeCellsS sSinLugar 

pesoNetoStack = netS sXX 

stackSobrePeso = holdsS sXL cMdq16 rutaLarga
stackDisponible = holdsS sXL cMdq rutaLarga

ultCiudadStack = ultimaCiudad [cMdq,cBue,cQeq]

sacarCon = popS sV2 qeq
noSaca1 = popS sV2 mvd
noSaca2 = popS sV2 bue

vesSinBahias = newV 0 0 rutaLarga  -- barco con ningun lugar
vesDos = newV 2 2 rutaLarga    -- barco con 2 bahias de altura 2. 4 celdas disponibles

vesCargado = loadV vesDos cMdq
vesCargado2 = loadV vesCargado cQeq
vesCargado3 = loadV vesCargado2 cMdq
vesCargado4 = loadV vesCargado3 cQeq
vesSobreCargado5 = loadV vesCargado4 cMdq

vesSinEseContenedor = unloadV vesCargado4 bue
vesCargado4Descargado = unloadV vesCargado4 mdq
vesCargado4Descargado2 = unloadV vesCargado4Descargado qeq

vesVacio = freeCellsV vesDos
vesLugar = freeCellsV vesCargado3
vesLleno = freeCellsV vesCargado4
vesSobreCargado = freeCellsV vesSobreCargado5



t = [ destinationC cMdq == "MDQ",
      pesoNetoC == 7,
      rutasEnOrden == True,     
      rutasNoEnOrden == False,  
      celdasLibresStack == 2,
      stackSinLugar == 0,
      pesoNetoStack == 10, 
      stackDisponible == True, 
      stackSobrePeso == False,
      ultCiudadStack == "QEQ",
      sacarCon == sV1,
      noSaca1 == sV2,
      noSaca2 == sV2,
      vesVacio == 4,
      vesLugar == 1,
      vesLleno == 0,
      vesSobreCargado == 0,
    

      testF cVacio, 
      testF cNegativo, 
      testF rVacia, 
      testF sCeroAlt,
      testF sAltNeg,      
    True ]
-- hacer un print de la lista t pero enumerando los resultados de cada test




testF :: Show a => a -> Bool                          -- cambiar la funcion para ver como probamos nosotros test for failure: ponerle cosas que van a dar error 
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just () 

