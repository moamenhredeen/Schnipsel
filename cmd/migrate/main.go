package migrate

import (
	"log"
	"os"

	"github.com/golang-migrate/migrate/v4"
)

func main() {
	connStr := os.Getenv("DATABASE_URL")
	m, err := migrate.New("file://internal/migrations", connStr)
	if err != nil {
		log.Fatal(err)
	}

	m.Up()
}
