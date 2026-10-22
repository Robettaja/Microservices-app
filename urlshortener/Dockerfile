FROM mcr.microsoft.com/dotnet/sdk:10.0 AS build
WORKDIR /src

COPY *.csproj ./
RUN dotnet restore

COPY . .
RUN dotnet publish -c Release -o /app/publish

FROM mcr.microsoft.com/dotnet/aspnet:10.0 AS final
WORKDIR /app

COPY --from=build /app/publish .

RUN mkdir -p /app/data

ENV ASPNETCORE_HTTP_PORTS=8080
ENV ConnectionStrings__DefaultConnection="Data Source=/app/data/urlshortener.db"

EXPOSE 8080

ENTRYPOINT ["dotnet", "urlshortener.dll"]
