package main

import (
	"context"
	"log"
	"net/http"
	"os"

	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"github.com/jackc/pgx/v5"
	"github.com/jackc/pgx/v5/pgtype"
	"github.com/joho/godotenv"
	"moamenhredeen.me/tue/db"
)

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}
	ctx := context.Background()
	connStr := os.Getenv("DATABASE_URL")
	conn, err := pgx.Connect(ctx, connStr)
	if err != nil {
		panic(err)
	}
	defer conn.Close(context.Background())
	queries := db.New(conn)

	router := chi.NewRouter()

	router.Use(middleware.RequestID)
	router.Use(middleware.Logger)
	router.Use(middleware.Recoverer)

	router.Get("/issue", func(w http.ResponseWriter, r *http.Request) {
		issues, err := queries.GetIssues(ctx)
		if err != nil {
			panic(err)
		}
		log.Println(issues)
		w.Write([]byte("success"))
	})

	router.Get("/issue/{id}", func(w http.ResponseWriter, r *http.Request) {
		issue, err := queries.GetIssueById(ctx, 1)
		if err != nil {
			panic(err)
		}
		log.Println(issue)
		w.Write([]byte("success"))
	})

	router.Get("/issue/create", func(w http.ResponseWriter, r *http.Request) {
		issue, err := queries.CreateIssue(ctx, db.CreateIssueParams{
			ID:      1,
			Title:   "issue title",
			Content: pgtype.Text{String: "aösdlkfajsdölfkj"},
		})

		if err != nil {
			panic(err)
		}

		log.Println(issue)
		w.Write([]byte("success"))
	})

	http.ListenAndServe(":3000", router)
}
