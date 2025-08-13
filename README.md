# Store Locator API
Este projeto é uma API REST desenvolvida para localizar lojas próximas a um endereço ou a coordenadas geográficas. O sistema combina geocodificação de endereços com cálculo de distância para encontrar lojas dentro de um raio definido.

## Funcionalidades
- Cadastrar lojas informando nome, endereço e outras informações relevantes.
- Buscar lojas próximas a um endereço — a API converte o endereço em coordenadas (latitude/longitude) usando a Nominatim API do OpenStreetMap.
- Buscar lojas próximas a coordenadas geográficas — o cliente envia diretamente latitude, longitude e raio de busca.
- Ordenar resultados pela distância em relação ao ponto de busca.

## Tecnologias Utilizadas
- Java 21
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Nominatim (OpenStreetMap)
- Fórmula de Haversine (cálculo da distância) 

## API de Geolocalização
API Nominatim, baseada no OpenStreetMap, foi utilizada para realizar a geocodificação de endereços. A API recebe um endereço textual fornecido pelo usuário e retorna as coordenadas geográficas correspondentes (latitude e longitude). Com essas coordenadas, a aplicação consegue calcular distâncias entre pontos e identificar quais lojas estão mais próximas, utilizando a fórmula de Haversine diretamente no banco de dados. O Nominatim é gratuito e aberto, permitindo buscas precisas sem depender de serviços pagos, mas exige que as requisições incluam um User-Agent para identificação da aplicação.

1. Endereço ou Coordenadas: Quando o usuário fornece um endereço ou coordenadas (latitude e longitude), a API usa Nominatim (OpenStreetMap) para o endereço em latitude e longitude.
2. Cálculo de distância: A aplicação calcula a distância entre o ponto informado e as lojas usando a fórmula de Haversine diretamente no banco de dados.
3. Filtragem por raio: Apenas lojas dentro do raio definido são retornadas, ordenadas da mais próxima para a mais distante.