(* 1.1 *)
let rec pow x n = match n with
|0 -> 1
|1 -> x
|_ -> x * pow x (n-1)
;;

let rec float_pow x n = match n with
|0 -> 1.0
|1 -> x
|_ -> x *. float_pow x (n-1)
;;

(* 1.2 *)
let reverse list =
  let rec helper ret = function
  |[] -> ret
  |h::t -> helper(h::ret) t in helper [] list
;;

(* 1.3 *)
let rec compress = function
|h::(h2::t2 as t) -> if h = h2 then compress(t) else h::compress(t)
|x -> x (* one or fewer elements *)
;;

(* 1.4 *)
let cluster list =
  let rec helper sublist ret = function
  |[] -> []
  |[x] -> (x::sublist)::ret
  |h::(h2::t2 as t) -> if h = h2 then helper(h::sublist) ret t
                       else helper [] ((h::sublist)::ret) t in 
                       reverse(helper [] [] list)
;;

(* 1.5 *)
let slice list first last =
  if first >= last then [] else
  let rec get x = function
  |[] -> []
  |h::t -> if x <= 0 then [] else h::get (x-1) t
  in
  let rec remove y = function
  |[] -> []
  |h::t as ret -> if y <= 0 then ret else remove(y-1) t
  in
  get(last - first) (remove first list)
;;

(* 2.1 *)
let composition f g =
  fun x -> f(g(x))
;;

(* 2.2 *)
let equiv_on f g lst =
  List.map f lst = List.map g lst
;;

(* 2.3 *)
let rec pairwisefilter cmp lst = match lst with
|[] -> []
|[x] -> [x]
|h::(h2::t2) -> cmp h h2::pairwisefilter cmp t2
;;

(* 2.4 *)
let poly_add f g =
  fun x -> (f x) + (g x)
;;

let rec polynomial = function
|[] -> fun x -> 0
|[(a,b)] -> fun x -> a * pow x b
|(a,b)::t -> poly_add (polynomial [(a,b)]) (polynomial t)
;;

let f = polynomial [3, 3; -2, 1; 5, 0];;
f 2;;

(* 3.1 *)
type bool_expr =
| Lit of string
| Not of bool_expr
| And of bool_expr * bool_expr
| Or of bool_expr * bool_expr
;;

let truth_table a b expr =
  let rec eval a lit_a b lit_b = function
  |Lit x -> if x = a then lit_a
            else if x = b then lit_b
            else failwith "Invalid literal"
  |Not e -> not(eval a lit_a b lit_b e)
  |And(e1, e2) -> eval a lit_a b lit_b e1 && eval a lit_a b lit_b e2
  |Or(e1, e2) -> eval a lit_a b lit_b e1 || eval a lit_a b lit_b e2
  in
  [(true, true, eval a true b true expr);
  (true, false, eval a true b false expr);
  (false, true, eval a false b true expr);
  (false, false, eval a false b false expr)]
;;

(* 3.2 *)
type 'a binary_tree =
|Empty
|Node of 'a * 'a binary_tree * 'a binary_tree
;;

(* 3.3 *)
let rec tree2str = function
  |Empty -> ""
  |Node(data, l, r) ->
     match l, r with
     |Empty, Empty -> string_of_int data
     |_,_ -> string_of_int data ^ "(" ^ (tree2str l) ^ "," ^ (tree2str r) ^ ")"
;;

