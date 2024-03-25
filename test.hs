import Stack 
import Route 
import Container 
import Vessel

import Control.Exception
import System.IO.Unsafe
import Data.List (elemIndex)

-- Ciudades
mdq = "MDQ"
rsl = "RSL"
bhi = "BHI"
bue = "BUE"
qeq = "QEQ"
mvd = "MVD"

--Contenedores
cMdq = newC mdq 5
cBue = newC bue 7
cQeq = newC qeq 9
cBhi = newC bhi 3
cRsl = newC rsl 1
cMdq16 = newC mdq 16

--Stacks
sLL = newS 3           
sXL = stackS sLL cMdq  
sXX = stackS sXL cMdq
sSinLugar = stackS sXX cMdq

sV0 = newS 2
sV1 = stackS sV0 cBue
sV2 = stackS sV1 cQeq

--Contenedores extras
cVacio = newC bue 0
cNegativo = newC bue (-2)
pesoNetoC = netC cBue

--Celdas, Stacks, etc
celdasLibresStack = freeCellsS sXL 
stackSinLugar = freeCellsS sSinLugar 
pesoNetoStack = netS sXX 
sCeroAlt = newS 0
sAltNeg = newS (-1)
stackSobrePeso = holdsS sXL cMdq16 rutaLarga
stackDisponible = holdsS sXL cMdq rutaLarga
ultCiudadStack = ultimaCiudad [cMdq,cBue,cQeq]

--Extras
sacarCon = popS sV2 qeq
noSaca1 = popS sV2 mvd
noSaca2 = popS sV2 bue

--Rutas
rutaLarga = newR [ bhi, qeq, mdq, bue, rsl ]     
rutaVacia = newR []
rVacia = newR []
rutasEnOrden = inOrderR rutaLarga bhi qeq
rutasNoEnOrden = inOrderR rutaLarga qeq bhi

--Vessels
vesSinBahias = newV 0 0 rutaLarga  
vesDos = newV 2 2 rutaLarga        
vesTres = newV 3 3 rutaLarga          
vesCuatro = newV 2 3 rutaLarga      

vesDosCargado = loadV vesDos cBue
vesDosCargado2 = loadV vesDosCargado cMdq
vesDosCargado3 = loadV vesDosCargado2 cQeq  
vesDosCargado4 = loadV vesDosCargado3 cBhi         
vesDosSobreCargado5 = loadV vesDosCargado4 cBhi

vesCuatroCargado = loadV vesCuatro cBue
vesCuatroCargado2 = loadV vesCuatroCargado cMdq
vesCuatroCargado3 = loadV vesDosCargado2 cBue
vesCuatroNoCargaPorRuta = loadV vesCuatroCargado3 cRsl

vesSinCont4 = unloadV vesDosCargado4 bhi
vesCargado4Descargado = unloadV vesDosCargado4 mdq
vesCargado4Descargado2 = unloadV vesCargado4Descargado qeq

vesVacio = freeCellsV vesDos
vesLugar = freeCellsV vesDosCargado3
vesLleno = freeCellsV vesDosCargado4
vesSobreCargado = freeCellsV vesDosSobreCargado5

netoVes = netV vesDosCargado4
netoVesVacio = netV vesSinBahias


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
      vesDosSobreCargado5 == vesDosCargado4,             
      vesCuatroNoCargaPorRuta == vesCuatroCargado3,      
      vesSinCont4 == vesDosCargado3,         
      netoVes == 24,           
      
      testF cVacio,                                     
      testF cNegativo,                                  
      testF rVacia,                                     
      testF sCeroAlt,                                   
      testF sAltNeg,                                    
    True                                                 
    ]




testF :: Show a => a -> Bool                          
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just () 

