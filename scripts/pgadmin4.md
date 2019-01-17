# Containers

## PGadmin

```
docker pull thajeztah/pgadmin4
```

## Utilização

### Remove o container se algum existir

```
docker run --rm -p 5050:5050 thajeztah/pgadmin4
```

### Permanece com o mesmo container

```
docker run -d -p 5050:5050 --name pgadmin --net pg thajeztah/pgadmin4
```
