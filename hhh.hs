lista = ["Buenos", "2"]
sacarTonelada = read (last lista) :: Int --m castear las toneladas de la lista

-- altura max bahia: 10 contenedores
-- max cant bahias: 10 

-- ruta1 = ["Paris","Roma","Rusia","Japon","Bolivia"]
-- Paris = 5c  
-- Roma = 3c 



toneladas = [1,2,3,4]
suma = sum toneladas

popS (Sta contenedores capacidad) ciudad = Sta (filter (\cont -> destinationC cont /= ciudad) contenedores) capacidad
