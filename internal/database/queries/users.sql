
-- name: GetAllUsers :many 
select * from users;

-- name: GetUserById :one
select * from users where id = $1;

-- name: DeleteUser :exec
delete from users where id = $1;