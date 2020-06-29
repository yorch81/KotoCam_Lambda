# KotoCam Lambda #

## Description ##
AWS Lambda Application for apply filters to images

## Requirements ##
* [Java](https://www.java.com/es/download/)
* [AWS Lambda](https://aws.amazon.com/es/lambda/)
* [AWS S3](https://aws.amazon.com/es/s3/)
* [JH Labs Filters](http://www.jhlabs.com/ip/filters/index.html)

## Developer Documentation ##
Execute mvn javadoc:javadoc

## Installation ##
Deploy in AWS Lambda

## Notes ##
Create Environment Variables for AWS Access: AWS_KEY & AWS_SECRET.

Create S3 Bucket for generated images.

## Filter List ##
~~~
Chrome
Crazy
Crystal
Edge
Gray
Invert
Mask
MixBlue
MixGreen
MixRed
Solarize
Tritone
Poster
Optimize
Points
Stained
Glass
Kaleidoscope
Mirror
Polar
Noise
DoG
~~~

## Testing ##
~~~
{
  "filter": "Invert",
  "scale": 50,
  "img": "base64 string"
}
~~~

## Example Response ##
~~~
{
  "s3Img": "https://s3_bucket_url/Invert_0311dd514f5c4e829aa4405fe4051c25.png"
}
~~~

## Example ##
~~~
curl -X POST -d '{
  "filter": "Edge",
  "scale": 50,
  "img": "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"
}' https://w3e5b4s1gl.execute-api.us-east-2.amazonaws.com/api
~~~

## References ##
https://docs.aws.amazon.com/lambda/latest/dg/java-package.html

P.D. Let's go play !!!







