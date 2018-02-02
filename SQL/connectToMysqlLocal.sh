echo "TABLE CONTACT"
mysql -u root -proot testdb < ./showC.sql 2>/dev/null
echo ""
echo "TABLE ENTREPRISE"
mysql -u root -proot testdb < ./showE.sql 2>/dev/null
echo ""
echo "TABLE CONTACTGROUP"
mysql -u root -proot testdb < ./showCG.sql 2>/dev/null
echo ""
echo "TABLE GROUPE"
mysql -u root -proot testdb < ./showG.sql 2>/dev/null
echo ""
echo "TABLE PHONENUMBER"
mysql -u root -proot testdb < ./showPN.sql 2>/dev/null
echo ""
echo "TABLE ADDRESS"
mysql -u root -proot testdb < ./showC.sql 2>/dev/null
echo ""