diff="var mins = ((new Date('9/13/13 $2')).getTime() - (new Date('9/13/13 $1')).getTime())/1000/60;"
mins="mins+' mins'"
hrs="((mins/60)+'').substr(0,5)+' hours'"
echo "$diff; console.log([$mins, $hrs].join('\n'))" | node
