# spring-boot-aws-sdk2

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-aws-sdk2/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-aws-sdk2/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![AWS SDK](https://img.shields.io/badge/AWS%20SDK-v2-FF9900?logo=amazonaws)

Sample Spring Boot application that talks to **Amazon S3** and **Amazon SQS** using the **AWS SDK for Java v2**.

## Tech Stack

| Component        | Version                     |
|------------------|-----------------------------|
| Java (JDK)       | 25                          |
| Spring Boot      | 4.1.x (Web + WebFlux)       |
| AWS SDK for Java | 2.x (`s3`, `sqs`)           |
| Build tool       | Maven (wrapper included)    |
| Lombok           | managed by Spring Boot      |

## Prerequisites

- JDK 25 (e.g. [Eclipse Temurin](https://adoptium.net/) or `sdk install java 25-tem`)
- An AWS account with an IAM user/role that has access to S3 and SQS
- An existing S3 bucket and SQS queue

## Configuration

All settings live in `src/main/resources/application.yml` and can be overridden with environment variables:

| Env variable             | Description                              | Default                   |
|--------------------------|------------------------------------------|---------------------------|
| `AWS_ACCESS`             | AWS access key ID                        | _(empty)_                 |
| `AWS_SECRET`             | AWS secret access key                    | _(empty)_                 |
| `AWS_ACCOUNT_NO`         | AWS account number (used for queue URL)  | _(empty)_                 |
| `S3_BUCKET`              | Target S3 bucket                         | `aws-bucket-sns-tutorial` |
| `S3_REGION`              | S3 region                                | `ap-south-1`              |
| `SQS_REGION`             | SQS region                               | `ap-south-1`              |
| `SQS_QUEUE_NAME`         | SQS queue name                           | `SQSQueueTutorial`        |
| `SQS_VISIBILITY_TIMEOUT` | Message visibility timeout (seconds)     | `30`                      |
| `SQS_MAX_MESSAGE`        | Max messages per receive call            | `10`                      |
| `SQS_WAIT_TIME`          | Long polling wait time (seconds)         | `20`                      |

> ⚠️ **Never commit real AWS credentials.** Provide them via environment variables (or an IAM role / AWS profile) instead of hard-coding them in `application.yml`.

Multipart upload limit is set to `1GB` (`spring.servlet.multipart.*`).

## Build & Run

```bash
# build & run tests
./mvnw clean verify

# run the application
export AWS_ACCESS=xxx AWS_SECRET=xxx AWS_ACCOUNT_NO=123456789012
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

## API Endpoints

### Amazon S3 — `/s3/buckets`

| Method   | Path                    | Params                                  | Description                         |
|----------|-------------------------|-----------------------------------------|-------------------------------------|
| `GET`    | `/s3/buckets`           | –                                       | List all buckets                    |
| `GET`    | `/s3/buckets/all`       | –                                       | List objects in configured bucket   |
| `POST`   | `/s3/buckets`           | `file` (multipart, one or more)         | Upload file(s)                      |
| `PUT`    | `/s3/buckets`           | `fileName`, `file` (multipart)          | Replace an existing object          |
| `DELETE` | `/s3/buckets`           | `fileName`                              | Delete an object                    |
| `POST`   | `/s3/buckets/multipart` | `file` (multipart, one or more)         | Upload large file(s) via multipart  |
| `POST`   | `/s3/buckets/download`  | `fileName`                              | Download an object                  |

```bash
curl -F "file=@./photo.png" http://localhost:8080/s3/buckets
curl -X DELETE "http://localhost:8080/s3/buckets?fileName=photo.png"
```

### Amazon SQS — `/sqs`

| Method | Path        | Body                   | Description                                  |
|--------|-------------|------------------------|----------------------------------------------|
| `GET`  | `/sqs`      | –                      | Receive messages and delete them in batch    |
| `POST` | `/sqs`      | plain string           | Send a single message                        |
| `POST` | `/sqs/bulk` | JSON array of strings  | Send messages in batch                       |

```bash
curl -X POST -H "Content-Type: text/plain" -d "hello" http://localhost:8080/sqs
curl -X POST -H "Content-Type: application/json" -d '["a","b"]' http://localhost:8080/sqs/bulk
curl http://localhost:8080/sqs
```

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests on Temurin **JDK 25** for every push / PR to `main`, and submits the Maven dependency graph on push to `main`.
- **Dependabot** (`.github/dependabot.yml`):
  - Maven — daily at 05:00 Asia/Jakarta, grouped PRs for AWS SDK, Spring, Maven plugins and other dependencies (minor/patch).
  - GitHub Actions — weekly (Monday), all updates grouped into one PR.
  - Labeled `dependencies` and using conventional commit prefixes (`chore(deps)`, `ci(deps)`).

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
