package issue

type IssueStatus string

const (
	IssueStatusOpen   IssueStatus = "open"
	IssueStatusClosed IssueStatus = "closed"
)

type Issue struct {
	ID          int
	Title       string
	Description string
	Status      IssueStatus
}

type CreateIssueRequest struct {
	Title       string
	Description string
}
