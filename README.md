# KotoCam Lambda #

## Description ##
AWS Lambda Application for apply filters to images

## Requirements ##
* [Java](https://www.java.com/es/download/)
* [AWS Lambda](https://aws.amazon.com/es/lambda/)
* [AWS S3](https://aws.amazon.com/es/s3/)
* [JH Labs Filters](http://www.jhlabs.com/ip/filters/index.html)

## Developer Documentation ##
JavaDoc.

## Installation ##
Deploy in AWS Lambda

## Notes ##
Create Environment Variables: AWS_KEY & AWS_SECRET.

Create S3 Bucket for generated images.

## Testing ##
~~~
{
  "filter": "Invert",
  "scale": "50",
  "img": "base64 string"
}
~~~

## Example Response ##
~~~
{
  "s3Img": "https://s3_bucket_url/Invert_0311dd514f5c4e829aa4405fe4051c25.png"
}
~~~

## References ##
https://docs.aws.amazon.com/lambda/latest/dg/java-package.html

P.D. Let's go play !!!







