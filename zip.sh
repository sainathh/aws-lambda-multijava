find ./ -name pom.xml | rev | cut -d/ -f2- | rev > location.txt

echo "Zip POM File Location!"
WORK_DIR=`pwd`
echo $WORK_DIR
while read p; do
  echo "Zip File Path: $p"
  export v=`echo "$p" | awk -F/ '{print $NF}'`
  echo "Zip File Name: $v"
  echo "Checkout to Lambda Path: $p"
  cd $p
  pwd
  zip -r "$v.zip"  ./*
  mv $v.zip $WORK_DIR
  echo "Checking Out to Root Directory"
  cd $WORK_DIR
done <location.txt
